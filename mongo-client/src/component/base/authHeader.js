import React from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from 'react-router-dom';
import Dropdown from "react-bootstrap/Dropdown";
import NavItem from "react-bootstrap/NavItem";
import NavLink from "react-bootstrap/NavLink";


export class AuthHeader extends React.Component {

    state = {
        user: '',
    };

    componentDidMount() {
        this.getUser();
    }

    getUser = () => {
        axios.get('http://localhost:8091/api/v1/user', { withCredentials: true })
            .then(response => this.setState({ user: response.data }));
    };

    deleteCookie = () => {
        axios.get('http://localhost:8091/api/v1/auth/sign-out', { withCredentials: true });
    };

    render() {
        return (
            <Dropdown as={NavItem}>
                <Dropdown.Toggle as={NavLink}>{this.state.user.nickName}</Dropdown.Toggle>
                <Dropdown.Menu className="dropdown-menu-right">
                    <Dropdown.Item as={Link} to={`user/${this.state.user.nickName}`}>My profile</Dropdown.Item>
                    <Dropdown.Item as={Link} to={`login`}>Log out</Dropdown.Item>
                    <Dropdown.Divider/>
                </Dropdown.Menu>
            </Dropdown>

        );
    }
}
export default AuthHeader;