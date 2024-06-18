import { imgMap } from "./data/imageMap";

export default function SingleNamePiece({selectedResource, resource, setResource}) {
    const classNameResource = "miniSquare" + (selectedResource == resource? " selected" : "");

    return <span className={classNameResource} onClick={() => setResource(resource)}>{imgMap[resource]}</span>;
}