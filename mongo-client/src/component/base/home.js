import React from 'react';
import {Badge, Container, InputGroup} from "react-bootstrap";
import {FormControl} from "react-bootstrap";
import {Button} from "react-bootstrap";
import {Card} from "react-bootstrap";
import {Post} from "../post/post";
import axios from "axios";
import WritePost from "../post/writePost";
import {CommentBox} from "../post/commentBox";
import {WriteComment} from "../post/writeComment";
import {ButtonGroup} from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import {DropdownButton} from "react-bootstrap";
import Form from "react-bootstrap/Form";

export class Home extends React.Component {
    state={
        posts: [],
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
            <div className="d-flex justify-content-lg-around">
            <Container style={{width: "60%"}}>
                <br/>
            <div className='align-content-center'  style={{height: "100px",  margin: "20px"}}>
                <WritePost posts = {this.state.posts}></WritePost>
                    <br/>
                {this.state.posts.map(element => (
                    <div>
                <Post
                    author = {element.authorNickName}
                    date = {element.date}
                    text = {element.text}
                    id = {element.id}>
                </Post>{' '}
                        {element.comments!=null &&
                            <div>
                        <CommentBox commentsAll = {this.state.posts.comments} id = {element.id} comments={element.comments}/>
                                </div>}
                        <br/>
                        <br/>
                    </div>
                ))}
            </div>
            </Container>
            </div>
        )
    }
}

