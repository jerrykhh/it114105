import React, {Component} from 'react';
import property from '../../server/property.json';
import MaterialTable from 'material-table';
import tableIcons from './TableIcon';

const rows = [];

const createData = (id, agentUsername, rental, sale, address, district, hidden, pin, size) => {
    return {id, agentUsername, rental, sale, address, district, hidden, pin, size};
}

property.forEach((obj, index) => {
    rows.push(createData(obj.uid, obj.agent, obj.rental,obj.sale, obj.address, obj.district, obj.hidden, obj.size))
});


class ManagerProperty extends Component{

    constructor(props){
        super(props);
        this.state = {
            rows
        }
    }

    render = () => {
        return(
            <MaterialTable
            title="Propety"
            icons={tableIcons}
            columns={[
              { title: '# id', field: 'id'},
              { title: 'Agent', field: 'agentUsername', type: "string" , defaultGroupOrder: 0  },
              { title: 'rental', field: 'rental', type: 'currency' },
              { title: 'Sale', field: 'sale', type: 'currency'},
              { title: 'Addres', field: 'address', type: 'string'},
              { title: 'District', field: 'district', type: 'string'},
              { title: 'Hidden', field: 'hidden', type: 'boolean'},
              { title: 'Pin', field: 'pin', type: 'boolean'},
              { title: 'Size', field: 'size', type: 'numeric'}
              
              
            ]}
            data={this.state.rows}        
            options={{
              grouping: true,
              pageSize: 10,
              pageSizeOptions: 10
            }}
            />
        );
    }
}

export default ManagerProperty;