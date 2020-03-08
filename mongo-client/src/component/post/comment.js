import React from "react";
import ListGroup from "react-bootstrap/ListGroup";
export class Comment extends React.Component{
    render() {
        return (
            <div>
                <div className="d-flex align-self-center" style={{height: '20px'}}>
                    <div className=" mr-auto p-2"><h6>{this.props.nickname}</h6></div>
                    <div className="p-2">{this.props.date}</div>
                </div>
                <br/>
                <p style={{margin: "6px"}}>{this.props.text}</p>
            </div>
        )
    }


}