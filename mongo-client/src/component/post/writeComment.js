import React from "react";
import axios from "axios";
import {Button, FormControl, InputGroup} from "react-bootstrap";
export class WriteComment extends React.Component{
    state={
        text: '',
    };
    setText=(e)=> {
        this.setState({
            text: e.target.value
        })
    };
    createComment=()=> {
        const data = {
            text: this.state.text,
            postId: this.props.id,
            status: 0,
        };
        axios.put('http://localhost:8091/api/posts/comment',
            data,
            {withCredentials: true})
            .then((response) => {
                this.setState({
                    status: response.status,
                });
            })
    };

    render(){
        return (
            <div style = {{ margin: "5px"}}className = "justify-content-lg-center">
                <InputGroup className="md-lg-3">
                    <FormControl onChange={this.setText}
                                 placeholder="Comment this.."
                                 aria-label=""/>
                    <InputGroup.Append>
                        <Button onClick={this.createComment} variant="outline-secondary" style={{background: "#40babf"}}>Comment</Button>
                    </InputGroup.Append>
                </InputGroup>
            </div>
        )
    }


}