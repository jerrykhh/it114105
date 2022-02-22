import React, {Component} from 'react';
import data from '../../server/appointment.json';
import user from '../../server/user.json';
import agent from '../../server/agent.json';
import { Redirect } from 'react-router-dom';
import MaterialTable from 'material-table';
import tableIcons from './TableIcon';


const rows = [];

const createData = (id, status,date,place, commentFromuser, commentFromAgent, username, agentName) =>{
     return {id, status, date, place, username, commentFromuser, agentName, commentFromAgent}
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
    rows.push(createData(obj.uid, obj.status, obj.date, obj.place, obj.cfu ,obj.cfa, username, agentName));
});

class ManagerAppointment extends Component{

    constructor(props){
        super(props);
        this.state = {
            rows,
            redirect: null
        };
    }
    render = () => {
        if(this.state.redirect != null){
            return <Redirect to={this.state.redirect} />;
        }
        return(
            <MaterialTable
            title="Appointment"
            icons={tableIcons}
            columns={[
              { title: '# id', field: 'id' },
              { title: "Status",field: "status", type: 'string', defaultGroupOrder: 0},
              { title: 'Date', field: 'date', type: "date" },
              { title: 'Place', field: 'place', type: 'string' },
              { title: 'Username(Customer)', field: 'username', type: 'string'},
              { title: 'Username(Agent)', field: 'agentName', type: 'string'}
              
              
            ]}
            data={this.state.rows}        
            options={{
              grouping: true,
              pageSize: 10,
              pageSizeOptions: 10,
              actionsColumnIndex: -1
            }}
            actions={[
                {
                  icon: tableIcons.View,
                  tooltip: 'View Appointment',
                  onClick: (event, rowData) => {
                      this.setState({redirect: '/manager/appointment/' + encodeURI(rowData.id)})
                  }
                }
            ]}
          />
        )
    }
}

export default ManagerAppointment;
