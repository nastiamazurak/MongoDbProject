import React from "react";
import {Card, FormControl, InputGroup} from "react-bootstrap";
import {Comment} from "./commentBox";
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
import Accordion from "react-bootstrap/Accordion";
import axios from "axios";

export class Post extends React.Component{

    state={
        posts: undefined,
        text: undefined,
        date: this.props.date,
        comment_data: [],
        status: "",
    };

    isEmptyField = () => this.state.text !== undefined && this.state.text !== '';


    setText=(e)=> {
        this.setState({
            text: e.target.value
        })
    };


    createComment=()=> {
        const comment_data = [this.props.id, this.state.text];
        axios.post('http://localhost:8091/api/posts/comment',
            comment_data,
            {withCredentials: true})
            .then((response) => {
                this.setState({
                    status: response.status,
                });
            })
    };

    formatDate(){
        var options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(this.state.date).toDateString([],options);
    }

    render() {
    console.log(this.state.text);
        return (
            <div className="p-2 bd-highlight">
                <Card border="primary">
                    <Card.Header>
                        <div className="d-flex align-self-center" style={{height: '20px'}}>
                            <div className=" mr-auto p-2">{this.props.author}</div>
                            <div className="p-2">{this.formatDate()}</div>
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Card.Title></Card.Title>
                        <Card.Text>
                                {this.props.text}
                                <div className="d-flex justify-content-lg-end">
                                    <Accordion defaultActiveKey="1">
                                        <Accordion.Toggle as={Button} variant="link" eventKey="0">
                                            Comment
                                        </Accordion.Toggle>
                                        <Accordion.Collapse eventKey="0">
                                            <InputGroup className="md-lg-3">
                                                <FormControl onChange={this.setText}
                                                             placeholder="Comment this.."
                                                             aria-label=""/>
                                                <InputGroup.Append>
                                                    <Button
                                                        onClick= {this.createComment}
                                                        disabled={!this.isEmptyField()}
                                                        variant="outline-secondary" style={{background: "#40babf"}}>Comment</Button>
                                                </InputGroup.Append>
                                            </InputGroup>
                                        </Accordion.Collapse>
                                    </Accordion>
                                    </div>

                        </Card.Text>
                    </Card.Body>
                </Card>
            </div>
        )
    }

}
export default Post;