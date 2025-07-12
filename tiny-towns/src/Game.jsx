import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import './Game.css';
import Button from "./Button";
import PlayerBoard from "./PlayerBoard";
import NamePiece from "./NamePiece";
import BuildingCards from "./BuildingCards";
import SingleNamePiece from "./SingleNamePiece";
import Score from "./Score";
import api, {API_BASE_URL} from "./api";
import Setup from "./Setup";
import ErrorBox from "./ErrorBox";
import Moves from "./Moves";
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import SelectPlayer from "./SelectPlayer";

export default function Game() {

    const {code} = useParams();

    const [errorMessage, setErrorMessage] = useState('');
    const [game, setGame] = useState(
        {
            "code": '',
            "players": [],
            "step": "SETUP",
            "resource": null,
            "cards": []
        }
    );

    const [currentPlayerId, setCurrentPlayerId] = useState(() => {
      return localStorage.getItem(`playerId-${code}`) || '';
    });

    const [needToSelectUser, setNeedToSelectUser] = useState(currentPlayerId === '');


    useEffect(() => {
        api.refresh(code)
        .then((response) => {
            setGame(response.data);
            const storedId = localStorage.getItem(`playerId-${code}`) || '';
            setCurrentPlayerId(storedId);
            if(storedId === '' && response.data.step !== 'SETUP') {
              setNeedToSelectUser(true);
            }
        }).catch(error => {
            setErrorMessage(error.userMessage);
        })
    }, [code]);

    useEffect(() => {
        const socket = new SockJS(`${API_BASE_URL.replace('http', 'https')}/ws`);
        const stompClient = new Client({
            webSocketFactory: () => socket,
            reconnectDelay: 5000,
            heartbeatIncoming: 10000,
            heartbeatOutgoing: 10000,
            onConnect: () => {
                stompClient.subscribe(`/topic/games/${code}`, message => {
                    const updatedGame = JSON.parse(message.body);
                    setGame(updatedGame);
                });
            },
            onStompError: (frame) => {
                console.error("STOMP error", frame.headers["message"], frame.body);
            }
        });
    
        stompClient.activate();
    
        return () => {
            stompClient.deactivate();
        };
    }, [code]);

    useEffect(() => {
        setErrorMessage('');
    }, [game]);
      
    const handleSelectCurrentPlayerId = (id) => {
      localStorage.setItem(`playerId-${code}`, id);
      setCurrentPlayerId(id);
      setNeedToSelectUser(false);
    }

    const handleJoinGame = (id) => {
      handleSelectCurrentPlayerId(id);
      setNeedToSelectUser(false);
    }

    const handleAddPlayer = (playerName) => {
        api.addPlayer(code, playerName).then((response) => {
          setGame(response.data.game);
          handleSelectCurrentPlayerId(response.data.playerId);
          setErrorMessage("");
        }).catch(error => {
          setErrorMessage(error.userMessage || "Unable to add player.");
        });
      };
      

      const handleStartGame = () => {
        api.startGame(code).then((response) => {
          setGame(response.data);
          setErrorMessage("");
        }).catch(error => {
          setErrorMessage(error.userMessage || "Unable to start the game.");
        });
      };

      const handleNamePiece = (piece) => {
        api.namePiece(code, piece).then((response) => {
          setGame(response.data);
          setErrorMessage("");
        }).catch(error => {
            console.log(error);
          setErrorMessage(error.userMessage || "Unable to name piece.");
        });
      };

      const handlePlacePiece = (playerId, gridIndex) => {
        api.placePiece(code, playerId, gridIndex).then((response) => {
          setGame(response.data);
          setErrorMessage("");
        }).catch(error => {
          setErrorMessage(error.userMessage || "Unable to place piece.");
        });
      };

      const handleBuild = (playerId, gridIndex, indexes, building) => {
        api.build(code, playerId, gridIndex, indexes, building).then((response) => {
          setGame(response.data);
          setErrorMessage("");
        }).catch(error => {
          setErrorMessage(error.userMessage || "Unable to build.");
        });
      };
      

      const handleEndTurn = () => {
        api.endTurn(code).then((response) => {
          setGame(response.data);
          setErrorMessage("");
        }).catch(error => {
          setErrorMessage(error.userMessage || "Unable to end turn.");
        });
      };

    const masterBuilder = game.players.find(player => player.masterBuilder);
    const currentPlayer = game.players.find(player => player.id == currentPlayerId);

    const cards = Object.values(game.cards);

    const playerBoards = game.players.map(player => {
        return <PlayerBoard key={player.id} handlePlacePiece={handlePlacePiece} handleBuild={handleBuild} step={game.step} player={player} buildings={cards} isCurrentPlayer={player.id == currentPlayerId}/>;
    })

    return <div className="game">
        {game.step == "SETUP"? 
        <Setup
            players={game.players}
            handleAddPlayer={handleAddPlayer}
            handleStartGame={handleStartGame}
            errorMessage={errorMessage}
            currentPlayerId={currentPlayerId}
            code={code}
        />
        :
            <>
                {needToSelectUser && 
                <SelectPlayer 
                    players={game.players} 
                    handleJoinGame={handleJoinGame}>
                </SelectPlayer>}
                <BuildingCards cards={cards}></BuildingCards>
                <ErrorBox message={errorMessage}></ErrorBox>
                <Moves
                    step={game.step}
                    masterBuilder={masterBuilder}
                    handleNamePiece={handleNamePiece}
                    handleEndTurn={handleEndTurn}
                    resource={game.resource}
                    currentPlayer={currentPlayer}
                ></Moves>
                 {
                    game.step == "END_GAME" &&
                    <Score players={game.players} cards={game.cards}></Score>
                 }
                <div className="players">
                {playerBoards}
                </div>
            </>
            
        }
    </div>;

}