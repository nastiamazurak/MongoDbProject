import React from "react";
import {Button, FormControl, InputGroup} from "react-bootstrap";
import axios from "axios";
export class WritePost extends React.Component{
    state={
        text: "",
        username: ""
    }
    cookiesToJson = () => Object.fromEntries(document.cookie.split(/; */).map((c) => {
        const [key, ...v] = c.split('=');
        return [key, decodeURIComponent(v.join('='))];
    }));

    isAuthorized = () => {
        const username = this.cookiesToJson().username;
        return  username ;
    };

    createPost=()=> {
        const data = {
            text: this.state.text,
            authorNickName:this.isAuthorized(),
            status: 0,
        };
        axios.post('http://localhost:8091/api/posts/create',
            data,
            {withCredentials: true})
            .then((response) => {
                this.setState({
                    status: response.status,
                });
            })
    };
    setPostText = (e) => {
        this.setState({ text: e.target.value });
    };
    componentDidMount() {
        this.createPost();
        this.isAuthorized();

    }

    render(){
        return (
            <InputGroup className="md-lg-3">
                <FormControl onChange={this.setPostText}
                    placeholder="Write a post.."
                    aria-label=""/>
                <InputGroup.Append>
                    <Button onClick={this.createPost} variant="outline-secondary" style={{background: "#40babf"}}>Post</Button>
                </InputGroup.Append>
            </InputGroup>
        )
    }
}

export default WritePost;