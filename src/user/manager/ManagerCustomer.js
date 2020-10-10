import React, {Component} from 'react';
import PageviewIcon from '@material-ui/icons/Pageview';
import userJson from '../../server/user.json';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';
import MaterialTable from 'material-table';
import tableIcons from './TableIcon';

function createData(id, username, email, name, tel, action) {
    return { id, username, email, name, tel, action };
}

const rows = [];

userJson.forEach((userObj, index) => {
    let fullName = userObj.firstName + " " + userObj.lastName;
    console.log(fullName);
    rows.push(createData(userObj.id, userObj.username, userObj.email, fullName, userObj.tel, <Link to={'customer/' + userObj.id} > <Button variant="contained" color="primary" size="small" startIcon={<PageviewIcon />}>View</Button> </Link>));
});

class ManagerCustomer extends Component {

    constructor(props){
        super(props);
        this.state = {
            rows
        }
    }

    deleteRowData = (rows) => {
        this.setState({rows});
        console.log("change");
    }

    render(){
        return(
            <MaterialTable
            icons={tableIcons}
            title="Customers"
            key={this.state.rows.length}
            columns={[
               {title: '# id', field: 'id', type: 'numeric'},
               {title: 'Username', field: 'username', type: 'string'},
               {title: 'Email', field: 'email', type: 'string'},
               {title: 'Name', field: 'name', type:'string'},
               {title: 'Tel', field: 'tel', type:'string'},
               {title: 'Action', field: 'action'}
            ]}
            data={this.state.rows}        
            options={{
              selection: true,
              pageSize: 10,
              pageSizeOptions: 10
            }}
            actions={[
              {
                tooltip: 'Remove All Selected Users',
                icon: 'delete',
                onClick: (evt, data) => this.state.rows.forEach((obj, index) => {
                    if(obj.id == data[0].id){
                        var newRows = this.state.rows;
                        newRows.splice(index);
                        this.deleteRowData(newRows);
                    }
                    console.log(data);
                })
              }
            ]}
          />
        )
        
    }
}

export default ManagerCustomer;