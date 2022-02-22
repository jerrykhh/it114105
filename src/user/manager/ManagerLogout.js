import React, {Component} from 'react';
import {Redirect} from 'react-router-dom';

class ManagerLogout extends Component {

    constructor(props){
        super(props);
        this.state = {

        }
    }

    render = () => {
        localStorage.clear();
        return(
            <Redirect to='/index'/>
        )
    }
}

export default ManagerLogout;