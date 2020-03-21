import React from "react";
import Button from "react-bootstrap/Button";
import {Modal} from "react-bootstrap";
import {Form} from "react-bootstrap";
import axios from 'axios';

export class UpdateInfoModal extends React.Component{

    constructor(props, context) {
        super(props, context);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: false,
            valid: undefined,
            name: '',
            surname: '',
            city: '',
            country: '',
            birthDate: ''
        };
    }
    handleShow() {
        this.setState({ show: true });
    }

    handleClose() {
        this.setState({
            show: false,
            valid: undefined,
        });
    }

    setName=(e)=>{
        this.setState({
            name: e.target.value,
    })
    };
    setSurname=(e)=>{
        this.setState({
            surname: e.target.value,
        })
    };
    setCity=(e)=>{
        this.setState({
            city: e.target.value,
        })
    };
    setCountry=(e)=>{
        this.setState({
            country: e.target.value,
        })
    };

    setBirthDate=(e)=>{
        this.setState({
            birthDate: e.target.value,
        })
    };

    updateUserInfo=()=>{
        const data = {
            name: this.state.name,
            surname: this.state.surname,
            //birthDate: this.state.birthDate,
            city: this.state.city,
            country: this.state.country,
            birthDate: this.state.birthDate
        };
        axios.put('http://localhost:8091/api/user',
            data,
            { withCredentials: true })
            .then((response) => {
                this.setState(
                    {
                        name: response.data.name,
                        surname: response.data.surname,
                        //birthDate: (response.data.birthDate).toDateString(),
                        city: response.data.city,
                        country: response.data.country,
                        birthDate: response.data.birthDate
                    },
                );

            });
    };
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
                                    value={this.props.firstName}
                                    onChange={this.setName}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Surname
                                </Form.Label>
                                <Form.Control
                                    value={this.props.surname}
                                    onChange={this.setSurname}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    City
                                </Form.Label>
                                <Form.Control
                                    value={this.props.city}
                                    onChange={this.setCity}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Country
                                </Form.Label>
                                <Form.Control
                                    value={this.props.country}
                                    onChange={this.setCountry}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>
                                    Birth Date
                                </Form.Label>
                                <Form.Control
                                    value={this.props.birthDate}
                                    onChange={this.setBirthDate}>
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