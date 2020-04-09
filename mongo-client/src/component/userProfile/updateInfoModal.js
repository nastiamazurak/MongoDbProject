import React from "react";
import Button from "react-bootstrap/Button";
import {Modal} from "react-bootstrap";
import {Form} from "react-bootstrap";
import axios from 'axios';

export class UpdateInfoModal extends React.Component{


    state = {
            show: false,
            valid: undefined,
            name: undefined,
            surname: '',
            city: '',
            country: '',
            birthDate: '',
            currentUser: {},
        };


    getCurrentUser = () => {
        axios.get('http://localhost:8091/api/v1/user', { withCredentials: true })
            .then(response => this.setState({ currentUser: response.data }));
    };

    handleUserInput = (e) => {
        const { name } = e.target;
        const { value } = e.target;
        this.setState({ [name]: value });
    };
    handleShow=()=> {
        this.setState({ show: true });
    }

    handleClose=()=> {
        this.setState({
            show: false,
            valid: undefined,
        });
    }

    updateUserInfo=()=>{
        const data = {
            name: this.state.name,
            surname: this.state.surname,
            birthDate: this.state.birthDate,
            city: this.state.city,
            country: this.state.country,
        };
        axios.put('http://localhost:8091/api/v1/user',
            data,
            { withCredentials: true })
            .then((response) => {
                this.setState(
                    {
                        name: response.data.name,
                        surname: response.data.surname,
                        //birthDate: (response.data.birthDate),
                        city: response.data.city,
                        country: response.data.country,
                        birthDate: new Date(response.data.birthDate)
                    },
                );
            });
    };

    componentDidMount() {
        this.getCurrentUser();
    }

    render() {
        return(
            <>
                <Button
                    variant="primary"
                    //style={{ margin: '17px', height: '45px', width: '125px' }}
                    onClick={this.handleShow}
                >
                    Update data
                </Button>

                <Modal centered show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Update User Info</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group>
                                <Form.Label>
                                    Name
                                </Form.Label>
                                <Form.Control
                                    name = "name"
                                    onChange={this.handleUserInput}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Surname
                                </Form.Label>
                                <Form.Control
                                    name = "surname"
                                    onChange={this.handleUserInput}
                                    >
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    City
                                </Form.Label>
                                <Form.Control
                                    name = "city"
                                    onChange={this.handleUserInput}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Country
                                </Form.Label>
                                <Form.Control
                                    name="country"
                                    onChange={this.handleUserInput}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Birth Date
                                </Form.Label>
                                <Form.Control
                                    onChange={this.handleUserInput}>
                                </Form.Control>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                            Close
                        </Button>
                        <Button onClick={this.updateUserInfo} variant="primary">
                            Update
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}
export default UpdateInfoModal;