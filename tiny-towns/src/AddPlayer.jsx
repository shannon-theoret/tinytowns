import { useState } from "react";
import Button from "./Button";
import './AddPlayer.css';

export default function AddPlayer({handleAddPlayer}) {

    const [playerName, setPlayerName] = useState("");
    const addPlayer = () => {
      if (!playerName.trim()) return;
      handleAddPlayer(playerName);
      setPlayerName("");
    }


    return (
      <div className="add-player">
        <input
          type="text"
          placeholder="Enter your name"
          value={playerName}
          onChange={(e) => setPlayerName(e.target.value)}
        />
        <Button onClick={addPlayer}>Join</Button>
      </div>
    );
}