import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import PaymentForm from "./PaymentForm";

export default function PaymentModal(props) {

  return (
    <>
      <Modal
        show={true}
        aria-labelledby="contained-modal-title-vcenter"
        centered
        onHide={() => props.setShowPayloadModal(false)}
      >
        <Modal.Header closeButton>
          <Modal.Title>Unesite podatke</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          
            <PaymentForm
              amount={props.amount}
            />
          
        </Modal.Body>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => props.setShowPayloadModal(false)}
          >
            Zatvori
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
