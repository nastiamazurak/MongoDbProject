import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import {AuthHeader} from "./authHeader";
import Nav from 'react-bootstrap/Nav';
import {Link} from 'react-router-dom';
import jwt from 'jwt-decode';

export class Head extends React.Component{

    cookiesToJson = () => Object.fromEntries(document.cookie.split(/; */).map((c) => {
        const [key, ...v] = c.split('=');
        return [key, decodeURIComponent(v.join('='))];
    }));

    state = {
        role:
            (this.cookiesToJson().JWT && jwt(this.cookiesToJson().JWT).roles),
    };


    isAuthorized = () => {
        const jwt = this.cookiesToJson().JWT;
        return jwt && jwt.length > 10;
    };

    render() {
        return (
            <Navbar  style={{background: "#2d8286"}}>
                <Navbar.Brand href="/" style={{color: "#b3e5dd"}} >Simple Facebook</Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse className="justify-content-end">
                    <Nav className="ml-auto" style={{color: "#b3e5dd"}}>
                        {this.isAuthorized() ?
                            <Nav>
                            <AuthHeader username = {this.isAuthorized()} />
                            </Nav>

                            : <Nav.Link as={Link} to="/login">Log in</Nav.Link>}
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        )
    }

}
export default Head;