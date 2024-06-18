import Button from "./Button"
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function NewGame() {

    let navigate = useNavigate();

    const newGame = () => {
        axios.post("/api/newGame").then((response) => {
            navigate(`/${response.data.code}`);
        })
    }

    return <Button onClick={newGame}>New Game</Button>;
}