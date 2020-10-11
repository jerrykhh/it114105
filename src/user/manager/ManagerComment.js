import React, {Component} from 'react';
import data from '../../server/appointment.json';
import user from '../../server/user.json';
import agent from '../../server/agent.json';
import MaterialTable from 'material-table';
import tableIcons from './TableIcon';

const rows = [];

const createData = (id,date, commentFromuser, commentFromAgent, username, agentName) =>{
     return {id, date, username, commentFromuser, agentName, commentFromAgent}
}

data.forEach((obj, index) => {
    var username = "";
    var agentName = "";
    user.forEach((userObj, index) => {
        userObj.appointments.forEach((item) => {
            if(item === obj.uid){
                username = userObj.username;
            }
        });
    });

    agent.forEach((agentObj, index) => {
        agentObj.appointments.forEach((item) => {
            if(item === obj.uid){
                agentName = agentObj.username;
            }
        });
    });
    if(obj.status === "finished")
        rows.push(createData(obj.uid, obj.date, obj.cfu ,obj.cfa, username, agentName));
});


class ManagerComment extends Component{
    constructor(props){
        super(props);
        this.state = {
            rows
        };
    }

    render = () => {
        return(
            <MaterialTable
            title="Comment"
            icons={tableIcons}
            columns={[
              { title: '# id', field: 'id', defaultGroupOrder: 0 },
              { title: 'Date', field: 'date', type: "date" },
              { title: 'Username(Customer)', field: 'username', type: 'string'},
              { title: 'Comment(Customer)', field: 'commentFromuser', type: 'string'},
              { title: 'Username(Agent)', field: 'agentName', type: 'string'},
              { title: 'Comment(Agent)', field: 'commentFromAgent', type: 'string'}
              
              
            ]}
            data={this.state.rows}        
            options={{
              grouping: true,
              pageSize: 10,
              pageSizeOptions: 10
            }}
          />
        )
    }
}

export default ManagerComment;