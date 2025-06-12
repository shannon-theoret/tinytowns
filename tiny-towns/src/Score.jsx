import React from "react";

import { imgMap } from './data/imageMap';
import './Score.css';


export default function Score({players, cards}) {
    const buildings = Object.keys(cards).filter(building => building !== 'RED_BUILDING');
    const calculateTotalScore = (player) => {
        const totalScore = buildings.reduce((acc, building) => acc + (player.score[building] || 0), 0);
        return totalScore - player.scorePenalty;
      };

    return (
        <div className="scoreContainer">
        <table className="score">
          <thead>
            <tr>
              <th>Building</th>
              {players.map(player => (
                <th key={player.id}>{player.name}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {buildings.map(building => (
              <tr key={building}>
                <td>{imgMap[building]}</td>
                {players.map(player => (
                  <td key={player.id}>{player.score[building]}</td>
                ))}
              </tr>
            ))}
            <tr key="penalty">
                <td>{imgMap["WOOD"]}</td>
                {players.map(player => (
                    <td key={player.id}>-{player.scorePenalty}</td>
                ))}
            </tr>
            <tr>
            <td>Total Score</td>
                {players.map(player => (
                <td key={player.id}>{calculateTotalScore(player)}</td>
                ))}
            </tr>
          </tbody>
        </table>
        </div>
      );
    };