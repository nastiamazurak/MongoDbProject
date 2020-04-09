import React from "react";
import {Button, FormControl, InputGroup} from "react-bootstrap";
import axios from "axios";

export class WritePost extends React.Component{

        state = {
            text: "",
            username: "",
            post: undefined,
            status: undefined
        };


    createPost=()=> {
        const data = {
            text: this.state.text,
            //authorNickName:this.isAuthorized(),
            status: 0,
        };
        axios.post('http://localhost:8091/api/posts/create',
            data,
            {withCredentials: true})
            .then((response) => {
                this.setState({
                    status: response.status,
                });
            });
        this.state.post = this.state.text;
    };

    setPostText = (e) => {
        this.setState({ text: e.target.value });
    };


    componentDidMount() {
        this.createPost();
    }
    isEmptyField = () => this.state.text !== undefined && this.state.text !== '';

    render(){
        return (
            <InputGroup className="md-lg-3">
                <FormControl onChange={this.setPostText}
                    placeholder="Write a post.."
                    aria-label=""/>
                <InputGroup.Append>
                    <Button disabled={!this.isEmptyField()} onClick={this.createPost} variant="outline-secondary" style={{background: "#40babf"}}>Post</Button>
                </InputGroup.Append>
            </InputGroup>
        )
    }
}

export default WritePost;