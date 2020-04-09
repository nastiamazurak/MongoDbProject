import React from "react";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit} from "@fortawesome/free-solid-svg-icons";
import {Button, Form, Modal} from "react-bootstrap";


export class EditPostModal extends React.Component {

    state = {
        show: false,
        text: undefined,
        currentUser: {},
    };

    editPost=()=>{
        const post_data = [this.props.id, this.state.text];
        axios.put(`http://localhost:8091/api/posts/${this.props.id}`,
            post_data,
            { withCredentials: true }).then(response => {
            this.setState({posts: response.data}
            )
        })
    };

    setText=(e)=> {
        this.setState({
            text: e.target.value
        })
    };

    handleShow=()=> {
        this.setState({ show: true });
    };

    handleClose=()=> {
        this.setState({
            show: false,
            valid: undefined,
        });
    };

    handleUserInput = (e) => {
        this.setState({ text: e.target.value });
    };

    render() {
        return (
            <div className="p-2">
                <FontAwesomeIcon onClick={this.handleShow} icon={faEdit}/>
                <div>
                    <Modal centered show={this.state.show} onHide={this.handleClose}>
                        <Modal.Header closeButton>
                        </Modal.Header>
                        <Modal.Body>
                            <Form>
                                <Form.Group>
                                    <Form.Label>
                                        Text
                                    </Form.Label>
                                    <Form.Control
                                        name="text"
                                        onChange={this.handleUserInput}>
                                    </Form.Control>
                                </Form.Group>
                            </Form>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={this.handleClose}>
                                Close
                            </Button>
                            <Button onClick={this.editPost} variant="primary">
                                Update
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </div>
            </div>
        )
    }

};

export default EditPostModal;