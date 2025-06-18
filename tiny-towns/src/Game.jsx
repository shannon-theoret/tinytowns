import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import './Game.css';
import Button from "./Button";
import PlayerBoard from "./PlayerBoard";
import NamePiece from "./NamePiece";
import BuildingCards from "./BuildingCards";
import SingleNamePiece from "./SingleNamePiece";
import Score from "./Score";
import api from "./api";
import Setup from "./Setup";
import ErrorBox from "./ErrorBox";
import Moves from "./Moves";

export default function Game() {

    const instructionMap = {
        "SETUP": "Add players and click Start Game when ready to begin",
        "TO_NAME": "Master builder name a type of resource",
        "TO_PLACE": "Players place your resource. Click on a square on the grid where you would like to place the piece.",
        "TO_BUILD": "Players may construct any buildings for which they have the matching resources in the correct shape. Select the building card and the corresponding resources on grid. Or end turn. Click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use.",
        "END_GAME": "The game has ended. See the scores below."
    }
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

    useEffect(() => {
        api.refresh(code)
        .then((response) => {
            setGame(response.data);
        }).catch(error => {
            setErrorMessage(error.userMessage);
        })
    }, [code]);

    useEffect(() => {
        setErrorMessage('');
    }, [game]);
      
    const handleAddPlayer = (playerName) => {
        api.addPlayer(code, playerName).then((response) => {
          setGame(response.data);
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

    const cards = Object.values(game.cards);

    const playerBoards = game.players.map(player => {
        return <PlayerBoard key={player.id} handlePlacePiece={handlePlacePiece} handleBuild={handleBuild} step={game.step} player={player} buildings={cards}/>;
    })

    return <div className="game">
        {game.step == "SETUP"? 
        <Setup
            players={game.players}
            handleAddPlayer={handleAddPlayer}
            handleStartGame={handleStartGame}
            errorMessage={errorMessage}
        />
        :
            <>
                <BuildingCards cards={cards}></BuildingCards>
                <ErrorBox message={errorMessage}></ErrorBox>
                <Moves
                    step={game.step}
                    masterBuilder={masterBuilder}
                    handleNamePiece={handleNamePiece}
                    handleEndTurn={handleEndTurn}
                    resource={game.resource}
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