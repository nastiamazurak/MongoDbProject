import React from 'react';
import {Container, InputGroup} from "react-bootstrap";
import {FormControl} from "react-bootstrap";
import {Button} from "react-bootstrap";
import {Card} from "react-bootstrap";
import {Post} from "../post/post";
import axios from "axios";

export class Home extends React.Component {
    state={
        posts: []
    };

    getPosts = ()=> {
        axios.get(`http://localhost:8091/api/posts/all`,
            { withCredentials: true }).then(response => {
            this.setState({posts: response.data}
            )
        })
    };

    componentDidMount() {
        this.getPosts();

    }
    render() {
        return (
            <Container style={{width: "60%"}}>
            <div className='align-content-center'  style={{height: "100px",  margin: "20px"}}>
                <InputGroup className="md-lg-3">
                    <FormControl
                        placeholder="Write a post.."
                        aria-label=""/>
                    <InputGroup.Append>
                        <Button variant="outline-secondary" style={{background: "#40babf"}}>Post</Button>
                    </InputGroup.Append>
                </InputGroup>
                <br/>
                {this.state.posts.map(element => (
                <Post
                    author = {element.authorNickName}
                    date = {element.date}
                    text = {element.text}>
                </Post>))}
            </div>
            </Container>
        )
    }
}

