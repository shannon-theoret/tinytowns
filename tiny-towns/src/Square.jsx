import { imgMap } from './data/imageMap';
import './Square.css';
export default function Square({index, step, indexToPlace, setIndexToPlace, indexToBuild, setIndexToBuild, indexesToBuild, setIndexesToBuild, squares, isCurrentPlayer}) {

    let selected = false;
    let alsoSelected = false;
    if (step === "PLACE") {
        selected = (index === indexToPlace);
    } else if (step === "BUILD") {
        selected = (index === indexToBuild);
        alsoSelected = (indexesToBuild.includes(index));
    }
    const squareClass = "square" + (selected? " selected" : "") + (alsoSelected? " alsoSelected" : "");
    const changeIndexToPlace = () => {
        if (index == indexToPlace) {
            setIndexToPlace();
        } else {
            setIndexToPlace(index);
        }
    }
    const selectIndexToBuild = () => {
        if (index == indexToBuild) {
            setIndexToBuild();
            setIndexesToBuild([]);
        } else if (indexToBuild == null && indexToBuild != 0) {
            setIndexToBuild(index);
            setIndexesToBuild([...indexesToBuild, index]);
        } else {
            if (indexesToBuild.includes(index)) {
                setIndexesToBuild(indexesToBuild.filter(item => item !== index));
            } else {
                setIndexesToBuild([...indexesToBuild, index]);
            }
        }
    }

    let onClick = null;
    if (step === "PLACE" && isCurrentPlayer) {
        onClick = changeIndexToPlace;
    }
    if (step === "BUILD" && isCurrentPlayer) {
        onClick = selectIndexToBuild;
    }

    return <span className={squareClass} onClick={onClick}>{imgMap[squares[index]]}</span>;
}
