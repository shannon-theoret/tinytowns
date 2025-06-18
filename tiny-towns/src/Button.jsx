import { Button as BootstrapButton } from 'react-bootstrap';

export default function Button(props) {
    return (
        <BootstrapButton
            variant='outline-dark'
            disabled={props.disabled}
            onClick={props.onClick}
        >
            {props.children}
        </BootstrapButton>
    );
}