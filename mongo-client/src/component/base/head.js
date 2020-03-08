import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import {AuthHeader} from "./authHeader";
import Nav from 'react-bootstrap/Nav';
import {Link} from 'react-router-dom';
import {Button} from "react-bootstrap";
import axios from "axios";

export class Head extends React.Component{

    state={
        status: undefined
    };

    cookiesToJson = () => Object.fromEntries(document.cookie.split(/; */).map((c) => {
        const [key, ...v] = c.split('=');
        return [key, decodeURIComponent(v.join('='))];
    }));

    isAuthorized = () => {
        const username = this.cookiesToJson().username;
        return  username ;
    };

    /*logOut=() => {
        axios.get(`http://localhost:8091/api/auth/sign-out`,
            { withCredentials: true }).then(response => {
            this.setState({status: response.status}
            )
        })
    };*/
    /*componentDidMount() {
        this.logOut();
    }*/

    render() {
        console.log(this.isAuthorized());
        return (
            <Navbar  style={{background: "#2d8286"}}>
                <Navbar.Brand href="/home" style={{color: "#b3e5dd"}} >Simple Facebook</Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse className="justify-content-end">
                    <Nav className="ml-auto" style={{color: "#b3e5dd"}}>
                        {this.isAuthorized() ?
                            <Nav>
                            <AuthHeader username = {this.isAuthorized()} />
                                <Nav.Link as = {Link} to="/login">Log out </Nav.Link>
                            </Nav>

                            : <Nav.Link as={Link} to="/login">Увійти</Nav.Link>}
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        )
    }

}
export default Head;