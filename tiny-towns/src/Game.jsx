import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import './Game.css';
import AddPlayer from "./AddPlayer";
import Button from "./Button";
import PlayerBoard from "./PlayerBoard";
import NamePiece from "./NamePiece";
import BuildingCards from "./BuildingCards";


export default function Game() {

    const instructionMap = {
        "SETUP": "Add players and click Start Game when ready to begin",
        "TO_NAME": "Master builder name a type of resource",
        "TO_PLACE": "Players place your resource. Click on a square on the grid where you would like to place the piece.",
        "TO_BUILD": "Players may construct any buildings for which they have the matching resources in the correct shape. Select the building card and the corresponding resources on grid. Or end turn. Click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use."
    }

    const {code} = useParams();
    const [errorMessage, setErrorMessage] = useState('');
    const [game, setGame] = useState(
        {
            "code": code,
            "players": [],
            "step": "SETUP",
            "resource": null,
            "cards": []
        }
    );

    useEffect(() => {
        axios.get(`/api/${code}`).then((response) => {
            setGame(response.data);
        });
    }, []);

    const startGame = () => {
        axios.post(`/api/${game.code}/startGame`).then((response) => {
            setGame(response.data);
        }); 
    }

    const playerNames = game.players.map(player => {
        return <p>{player.name}</p>;
    })

    const cards = Object.values(game.cards);

    const playerBoards = game.players.map(player => {
        return <PlayerBoard gameCode={game.code} step={game.step} setGame={setGame} player={player} buildings={cards} setErrorMessage={setErrorMessage}/>;
    })

    return <div className="game">
        <div className="gameCode">{game.code}</div>
        <p>{instructionMap[game.step]}</p>
        <p>{errorMessage}</p>
        {game.step == "SETUP" &&
        <>
        <div>
            {playerNames}
        </div>
        <AddPlayer gameCode={game.code} setGame={setGame}/>
        <Button onClick={startGame}>Start Game</Button>
        </>
        }

        {
            game.step != "SETUP" &&
            <>
                <BuildingCards cards={cards}></BuildingCards>
            {
                game.step == "TO_NAME" &&
                <NamePiece gameCode={game.code} setGame={setGame}></NamePiece>
                 }
                <div className="players">
                {playerBoards}
                </div>
            </>
            
        }
    </div>;

}