import { imgMap } from './data/imageMap';

export default function Square({index, step, indexToPlace, setIndexToPlace, indexToBuild, setIndexToBuild, indexesToBuild, setIndexesToBuild, squares}) {


    let selected = false;
    let alsoSelected = false;
    if (step === "TO_PLACE") {
        selected = (index === indexToPlace);
    } else if (step === "TO_BUILD") {
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
    if (step === "TO_PLACE") {
        onClick = changeIndexToPlace;
    }
    if (step === "TO_BUILD") {
        onClick = selectIndexToBuild;
    }
    return <span className={squareClass} onClick={onClick}>{imgMap[squares[index]]}</span>;
}
