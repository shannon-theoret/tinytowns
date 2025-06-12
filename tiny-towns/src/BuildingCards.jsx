import { buildingCardMap } from "./data/buildingCardMap";
import './BuildingCards.css';

export default function BuildingCards({cards}) {
    const cardImages = cards.map(card => {
        return buildingCardMap[card].image;
    })

    return <div className="buildingCards">{cardImages}</div>
}