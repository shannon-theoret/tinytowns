import './Button.css';

export default function Button(props) {
    return <button disabled={props.disabled} onClick={props.onClick}>{props.children}</button>;
}