import React from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import {Link, Redirect} from 'react-router-dom'
import {Button} from "react-bootstrap";

export class Registration extends React.Component {
    state = {
        nickName: undefined,
        password: undefined,
        confirmPassword: undefined,
        name: undefined,
        surname: undefined,
        dublicatedNickname: undefined,
        invalidPassword: undefined,
        status: undefined,
        redirect: false
    };
    setNickname = (e) => {
        this.setState({
            nickname: e.target.value,
            dublicatedNickname: undefined,
        });
    };

    setPassword = (e) => {
        this.setState({
            password: e.target.value,
            invalidPassword: undefined,
        });
    };

    setFirstName = (e) => {
        this.setState({ name: e.target.value });
    };

    setLastName = (e) => {
        this.setState({ surname: e.target.value });
    };

    setConfirmPassword = (e) => {
        this.setState({ confirmPassword: e.target.value });
    };

    isValidForm = () => this.state.confirmPassword === this.state.password
        && this.isEmptyField();

    isEmptyField = () => this.state.name !== undefined && this.state.name !== ''
        && this.state.surname !== undefined && this.state.surname !== ''
        && this.state.nickname !== undefined && this.state.nickname !== ''
        && this.state.password !== undefined && this.state.password !== ''
        && this.state.confirmPassword !== undefined && this.state.confirmPassword !== '';

    isVisible = () => this.state.status === 200;


    insertRegistrationData = () => {
        const data = {
            name: this.state.name,
            surname: this.state.surname,
            nickName: this.state.nickname,
            password: this.state.password,
            status: 0,
        };
        axios.post('http://localhost:8091/api/v1/auth/register',
            data,
            {withCredentials: true})
            .then((response) => {
                this.setState({
                    status: response.status,
                });
            })
    };

    setRedirect = () => {
        this.setState({
            redirect: true
        })
    };
    renderRedirect = () => {
        if (this.state.redirect) {
            return <Redirect to='/login' />
        }
    };

    render() {
        return (

            <div align="center" >
                <br/>
                <h2>Registration</h2>
                <div className="col-3 ">
                    <Form.Group controlId="formForFirstName">
                        <Form.Control type="firstName" placeholder="Enter your name" onChange={this.setFirstName} />
                    </Form.Group>
                </div>

                <div className="col-3">
                    <Form.Group controlId="formForLastName">
                        <Form.Control
                            type="lastName"
                            placeholder="Enter your surname"
                            onChange={this.setLastName}
                        />
                    </Form.Group>
                </div>

                <div className="col-3">
                    <Form.Group controlId="formText">
                        <Form.Control
                            type="text"
                            placeholder="Enter your nickname"
                            onChange={this.setNickname}
                            isInvalid={ !!this.state.dublicatedNickname}
                        />
                        <Form.Control.Feedback type="invalid">
                            {this.state.dublicatedNickname}
                        </Form.Control.Feedback>
                    </Form.Group>
                </div>

                <div className="col-3">
                    <Form.Group controlId="formPlaintextPassword">
                        <Form.Control
                            type="password"
                            placeholder="Enter password"
                            onChange={this.setPassword}
                            isInvalid={!!this.state.invalidPassword}
                        />
                        <Form.Control.Feedback type="invalid">
                            {this.state.invalidPassword}
                        </Form.Control.Feedback>
                    </Form.Group>
                </div>

                <div className="col-3">
                    <Form.Group controlId="formPlaintextPassword2">
                        <Form.Control
                            type="password"
                            placeholder="Confirm your password"
                            onChange={this.setConfirmPassword}
                        />
                    </Form.Group>
                </div>

                <div className="col-3">
                    {' '}
                    {!this.isEmptyField() && <div className="alert alert-primary" role="alert">All fields must be filled in</div>}
                </div>

                <button
                    className="btn btn-success"
                    onClick={this.insertRegistrationData}
                    disabled={!this.isValidForm()}
                >
                    Register
                </button>
                <p> Already have an account?</p>
                <Button as={Link} to="/login" className="btn btn-success" size="sm">Login</Button>

                <div>
                    {' '}
                    {this.isVisible()
                    && <div>
                        <br/>
                        <h5>You was registered successfully!</h5>
                        <br/>
                        <Link to='/login' > Go to login page </Link>
                    </div>}
                </div>

            </div>

        );
    }
}
export default Registration;