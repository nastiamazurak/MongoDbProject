import React from "react";
import axios from "axios";
import {Card} from "react-bootstrap";

export class Post extends React.Component{
    state={
        posts: undefined
    };

    render() {

        return (
            <div className="p-2 bd-highlight">
                <Card border="primary">
                    <Card.Header>
                        <div className="d-flex align-self-center" style={{height: '20px'}}>
                            <div className=" mr-auto p-2">{this.props.author}</div>
                            <div className="p-2">{this.props.date}</div>
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Card.Title></Card.Title>
                        <Card.Text>
                            {this.props.text}
                        </Card.Text>
                    </Card.Body>
                </Card>
            </div>
        )
    }

}
export default Post;