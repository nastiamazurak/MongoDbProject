import React from 'react';
import { Route, Switch } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import {Login} from './component/auth/login';
import {Home} from './component/base/home'
import {Registration} from './component/auth/registration'
import { Head } from './component/base/head';
import {Post} from "./component/post/post";
import UserProfile from "./component/userProfile/userProfile";

export class Routers extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <Head />
                <Switch>
                    <Route exact path="/" component={Home} />
                    {/*<Route exact path="/" component={Base} />*/}
                    <Route path="/login" component={Login} />
                    <Route path="/registration" component={Registration} />
                    <Route path="/posts" component={Post}/>
                    <Route path="/user/:username"  render={props => <UserProfile {...props} username={this.props.username} />}/>

                </Switch>
            </BrowserRouter>
        );
    }
}