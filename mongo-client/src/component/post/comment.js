import React from "react";
import ListGroup from "react-bootstrap/ListGroup";
import {Link} from "react-router-dom";
export class Comment extends React.Component{

    state={
        date: this.props.date
    };

    formatDate(){
        var options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(this.state.date).toDateString([],options);
    }


    render() {
        return (
            <div>
                <div className="d-flex align-self-center" style={{height: '20px'}}>
                    <div className=" mr-auto p-2"><h6>
                        <Link style={{color: "#0097a7"}} to={`user/${this.props.nickname}`}>@{this.props.nickname}</Link></h6></div>
                    <div className="p-2">{this.formatDate()}</div>
                </div>
                <br/>
                <p style={{margin: "6px"}}>{this.props.text}</p>
            </div>
        )
    }


}