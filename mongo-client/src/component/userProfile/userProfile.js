import React from "react";
import axios from "axios";
import userAvatar from "/Users/nastiamazurak/Desktop/SimpleFacebook/mongo-client/src/component/image/avatar.png"
import {Container} from "react-bootstrap";
import {Row} from "react-bootstrap";
import {Col} from "react-bootstrap";
import {ListGroup} from "react-bootstrap";
import {Post} from "../post/post";
import WritePost from "../post/writePost"
import UpdateInfoModal from "./updateInfoModal";
import {CommentBox} from "../post/commentBox";
import jwt from "jwt-decode";


export class UserProfile extends React.Component{

    cookiesToJson = () => Object.fromEntries(document.cookie.split(/; */).map((c) => {
        const [key, ...v] = c.split('=');
        return [key, decodeURIComponent(v.join('='))];
    }));

    state = {
        role:
            (this.cookiesToJson().JWT && jwt(this.cookiesToJson().JWT).roles),
        user: {},
        currentUser: {},
        posts:[],
        username: this.props.match.params.username,
        commentsNumber: undefined
    };


    isAuthorized = () => {
        const jwt = this.cookiesToJson().JWT;
        return jwt && jwt.length > 10;
    };

    getCurrentUser = () => {
        axios.get('http://localhost:8091/api/v1/user', { withCredentials: true })
            .then(response => this.setState({ currentUser: response.data }));
    };


    getUserInfo=()=>{
        axios.get(`http://localhost:8091/api/v1/user/${this.state.username}`,
            { withCredentials: true }).then(response => {
            this.setState({user: response.data}
            )
        })
    };

    getUserPosts=()=>{
        axios.get(`http://localhost:8091/api/posts/${this.state.username}`,
            { withCredentials: true }).then(response => {
            this.setState({posts: response.data}
            )
        })
    };

    componentDidMount() {
        this.isAuthorized();
        this.cookiesToJson();
        this.getUserInfo();
        this.getUserPosts();
        this.getCurrentUser();
    }

    formatDate(){
       var options = { month: 'long', year: 'numeric', day: 'numeric'};
       return new Date(this.state.user.birthDate).toLocaleDateString('en-GB', [],options)
    }

    hasUserAccess = () => this.state.username === this.state.currentUser.nickName;

    render() {
        console.log(this.state.user.name);

        return(
            <Container>
                <Row>
                    <Col style={{height: "40%", width:"40%"}}>
                        <img style={{ width:"55%", marginTop:"5%"}}
                             src={userAvatar} alt='avatar'/>
                    </Col>
                    <Col style={{height: "40%", width:"40%"}}>
                        <h1 style={{marginTop:"5%"}}>User Information</h1>
                        <br/>
                        <ListGroup variant="flush">
                            <ListGroup.Item>Birth Date: {this.formatDate()}</ListGroup.Item>
                            <ListGroup.Item>Country: {this.state.user.country}</ListGroup.Item>
                            <ListGroup.Item>City: {this.state.user.city}</ListGroup.Item>
                            {' '}
                            {this.hasUserAccess()
                            &&
                            <ListGroup.Item>
                                <UpdateInfoModal firstName={this.state.user.name}
                                surname = {this.state.user.surname}
                                city = {this.state.user.city}
                                country = {this.state.user.country}
                                birthDate = {this.formatDate()}/>
                            </ListGroup.Item>}
                        </ListGroup>
                    </Col>
                </Row>
                <Row>
                    <div className="col-6" style={{width: "66%"}}>
                        <br/>
                        <h1>{this.state.user.name} {this.state.user.surname}</h1>
                        <h3 style={{color:"#4db6ac"}}>@{this.state.user.nickName}</h3>
                    </div>
                    <div className="col-6" style={{width: "66%"}}>
                        <br/>
                        <h3>User Statistics</h3>
                        <ListGroup>
                            <ListGroup.Item>Total posts: {this.state.posts.length}</ListGroup.Item>
                        </ListGroup>
                    </div>
                </Row>
                <br/>
                <div className="align-content-center" style={{height: "30%",  margin: "20px"}}>
                    {' '}
                    {this.hasUserAccess()
                    &&
                    <WritePost posts = {this.getUserPosts()}></WritePost>}
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
                    </div>))}
                </div>
            </Container>
        )
    }
}
export default UserProfile;