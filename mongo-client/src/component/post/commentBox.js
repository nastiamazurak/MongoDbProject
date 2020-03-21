import React from "react";
import Accordion from "react-bootstrap/Accordion";
import Card from "react-bootstrap/Card";
import {Button} from "react-bootstrap";
import ListGroup from "react-bootstrap/ListGroup";
import {Comment} from "./comment";

export class CommentBox extends React.Component{

    state={
        commentsNumber: undefined
    };

    render(){
        let CommentButton;
        if (this.props.comments.length > 1) {
            CommentButton = <Accordion.Toggle as={Button} variant="link" eventKey="0">
                {this.props.comments.length} Comments
            </Accordion.Toggle>;
        } else {
            CommentButton = <Accordion.Toggle as={Button} variant="link" eventKey="0">
                {this.props.comments.length} Comment
            </Accordion.Toggle>;
        }
        return(

            <Accordion defaultActiveKey="1">
            <Card style={{ margin: "6px"}}>
                <Card.Header>
                    {CommentButton}
                </Card.Header>
                <Accordion.Collapse eventKey="0">
                <ListGroup variant="flush">
                    {this.props.comments.map(element => (
                    <ListGroup.Item>
                        <Comment nickname={element.author_nickname} text={element.text}
                        date={element.date}/>
                    </ListGroup.Item>))}
                </ListGroup>
                </Accordion.Collapse>
            </Card>
            </Accordion>
        )}
}