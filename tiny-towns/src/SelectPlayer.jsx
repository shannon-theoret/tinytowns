import { useState } from "react";
import Button from "./Button";
import Dropdown from "./Dropdown";
import './SelectPlayer.css';

export default function SelectPlayer({players, handleJoinGame}) {

    const playerOptions = players.map(player => {
        return {displayName: player.name, value: player.id};
    })

    const [playerId, setPlayerId] = useState('');

    const handlePlayerSelection = (event) => {
        setPlayerId(event.target.value);
    }

    return (
    <div className="select-player">
        <Dropdown
                prompt="Which player are you?"
                options={playerOptions}
                onChange={handlePlayerSelection}
            ></Dropdown>
            <Button onClick={() => {handleJoinGame(playerId)}}disabled={playerId===''}>Join Game</Button>
            </div>
    );
}