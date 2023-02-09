import { MenuItem } from "@mui/material";
import { TextField } from 'formik-mui';
import { useEffect, useState } from "react";

const GroupType = (props) => {

    const [types, setType] = useState([]);

    useEffect(() => {
        const loadData = () => {
            setType(["IPs", "Ports", "IPs/Ports"])
        }
        loadData();
    }, []);

    return (
        <TextField 
            select
            {...props}
        >
            {types.length ?
                types.map((type) => (
                    <MenuItem key={type} value={type}>
                        {type}
                    </MenuItem>
                ))
                :
                <MenuItem>loading...</MenuItem>
            }
        </TextField >
    );

};

export default GroupType;