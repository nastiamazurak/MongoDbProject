import React from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import {Form, Modal} from "react-bootstrap";
import ListGroup from "react-bootstrap/ListGroup";
import {Link} from "react-router-dom";

export class FriendsList extends React.Component {
    state={
        show: false,
        valid: undefined,
        areFriends: true

    }
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
    areFriends=()=>{
        if (this.props.friends == null){
            this.setState({
                areFriends: false
            })
        }
        return this.state.areFriends;
    }

    render() {
        return(
            <>
                <Button
                    variant="link"
                    //style={{ margin: '17px', height: '45px', width: '125px' }}
                    onClick={this.handleShow}
                >
                    Following: {this.props.friends.length}
                </Button>

                {this.areFriends &&
                <Modal centered show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Following: </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ListGroup>
                            {this.props.friends.map (element => (
                            <ListGroup.Item>
                                <Link style={{color: "#0097a7"}} to={`/user/${element}`}>@{element}</Link>
                            </ListGroup.Item>))}
                        </ListGroup>
                    </Modal.Body>
                </Modal>}
            </>
        )
    }
                                
}