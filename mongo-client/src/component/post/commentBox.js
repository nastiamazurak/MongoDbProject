import React from "react";
import Accordion from "react-bootstrap/Accordion";
import Card from "react-bootstrap/Card";
import {Button} from "react-bootstrap";
import ListGroup from "react-bootstrap/ListGroup";
import {Comment} from "./comment";
import {WriteComment} from "./writeComment";

export class CommentBox extends React.Component{

    render(){
        return(

            <Accordion defaultActiveKey="1">
            <Card style={{ margin: "6px"}}>
                <Card.Header>
                <Accordion.Toggle as={Button} variant="link" eventKey="0">
                    Comments
                </Accordion.Toggle>
                </Card.Header>
                <Accordion.Collapse eventKey="0">
                <ListGroup variant="flush">
                    {this.props.comments.map(element => (
                    <ListGroup.Item>
                        <Comment nickname={element.author_nickname} text={element.text}
                        date={element.date}/>
                    </ListGroup.Item>))}
                </ListGroup>
                    {/*<WriteComment id={this.props.postId}/>*/}
                </Accordion.Collapse>
            </Card>
            </Accordion>



    )}
}