import React, { useState } from "react";
import Button from "./Button";
import './NamePiece.css';
import SingleNamePiece from "./SingleNamePiece";

export default function NamePiece({handleNamePiece}) {

    const [resource, setResource] = useState("");

    return <>
        <div className="nameResource">
        <SingleNamePiece selectedResource={resource} resource="WOOD" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="WHEAT" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="STONE" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="BRICK" setResource={setResource}/>
        <SingleNamePiece selectedResource={resource} resource="GLASS" setResource={setResource}/>    
        </div>
        <Button disabled={!resource} onClick={() => handleNamePiece(resource)}>Name Resource</Button>
        </>;
}
