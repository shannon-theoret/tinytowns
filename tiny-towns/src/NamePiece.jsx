import React, { useState } from "react";
import axios from "axios";
import Button from "./Button";
import './NamePiece.css';
import SingleNamePiece from "./SingleNamePiece";

export default function NamePiece(props) {

    const [resource, setResource] = useState("");

    const namePiece = () => {
        axios.post(`/api/${props.gameCode}/namePiece`, null, {
            params: {
                piece: resource
            }
        }).then((response) => {
            props.setGame(response.data);
        });       
    }

    return <>
        <div className="nameResource">
        <SingleNamePiece selectedResource={resource} resource="WOOD" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="WHEAT" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="STONE" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="BRICK" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="GLASS" setResource={setResource}/>    
        </div>
        <Button disabled={!resource} onClick={namePiece}>Name Resource</Button>
        </>;
}
