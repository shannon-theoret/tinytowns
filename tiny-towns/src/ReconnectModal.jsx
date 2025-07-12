import React, { useState } from 'react';
import { Form, Modal, Button } from 'react-bootstrap';

function ReconnectModal({ show, handleClose, handleRefresh }) {

    const [dontShowAgain, setDontShowAgain] = useState(false);

  return (
    <Modal show={show && !dontShowAgain} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Connection Timed Out</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        Still there? The game may have lost its connection. Your opponent may have made a move. Refreshing might help.
        <Form.Check 
            type='checkbox'
            id="dontShowAgain"
            label="Don't show this again"
            checked={dontShowAgain}
            onChange={(e) => setDontShowAgain(e.target.checked)}
        />
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Dismiss
        </Button>
        <Button variant="secondary" onClick={handleRefresh}>
          Refresh Now
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ReconnectModal;