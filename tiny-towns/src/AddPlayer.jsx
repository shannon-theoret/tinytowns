import axios from "axios";
import { useState } from "react";
import Button from "./Button";

export default function AddPlayer(props) {

    const [playerName, setPlayerName] = useState("");

    const addPlayer = () => {
        if ( playerName.length > 0) {
            axios.post(`/api/${props.gameCode}/addPlayer`, null, {params: {playerName: playerName}}).then((response) => {
                props.setGame(response.data);
                setPlayerName("");
            });   
        }
    }

    return <div>
      <label>Enter your name:
        <input
          type="text" 
          value={playerName}
          onChange={(e) => setPlayerName(e.target.value)}
        />
      </label>
        <Button onClick={addPlayer}>Add Player</Button>
    </div>;

}