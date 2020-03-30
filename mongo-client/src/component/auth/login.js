import React from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import {Redirect} from 'react-router-dom'
import {Button} from "react-bootstrap";
import { Link } from 'react-router-dom';
export class Login extends React.Component {
    state = {
        nickName: undefined,
        password: undefined,
        status: undefined,
        errorMessage: undefined
    };

    setNickName = (nickName) => {
        this.setState({nickName: nickName.target.value});
    };

    setPassword = (pass) => {
        this.setState({password: pass.target.value});
    };

    insertLoginData = () => {
        let data = {
            nickName: this.state.nickName,
            password: this.state.password
        };

        axios.post(`http://localhost:8091/api/v1/auth/sign-in`,
            data,
            {withCredentials: true})
            .then(response => {
                this.setState({
                    status: response.status
                })
            })
            /*.catch(err => {
                this.setState({errorMessage: err.response.data["message"]});
                console.log(err.response.data);
            })*/
    };
    isEmptyField = () => {
        return this.state.nickName !== undefined && this.state.nickName  !== "" &&
            this.state.password !== undefined && this.state.password !== ""
    };

    isVisible = () => {
        return this.state.status === 200
    };

    render() {
        return (

            <div align="center" >
                <br/>
                <h2>Please sign in</h2>
                <div className="col-3">
                    <Form.Group controlId="formText">
                        <Form.Control
                            type="text"
                            placeholder="Enter your nickname"
                            onChange={this.setNickName}
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
                    {' '}
                    {!this.isEmptyField() && <div className="alert alert-primary" role="alert">All fields must be filled in</div>}
                </div>

                <button
                    className="btn btn-success"
                    onClick={this.insertLoginData}
                >
                    Log in
                </button>
                <p>Don't have an account?</p>
                <Button as={Link} to="/registration" className="btn btn-success" size="sm">Register</Button>
                <div>
                    {' '}
                    {this.isVisible()
                    && <div>
                        <br/>
                        <Redirect to='/' />
                    </div>}
                </div>
            </div>

        );
    }
}
export default Login;