import { MenuItem, Box } from "@mui/material";
import { TextField, Select } from 'formik-mui';
import { useEffect, useState } from "react";
const Modes = (props) => {

    const [modes, setModes] = useState([]);

    useEffect(() => {
        const loadData = () => {
            setModes(["USE GROUPS", "CREATE SINGLE RULE"])
        }
        loadData();
    }, []);

    return (
        <TextField 
            select
            {...props}
        >
            
            {modes.length ?
                modes.map((mode) => (
                    <MenuItem key={mode} value={mode}>
                        {mode}
                    </MenuItem>
                ))
                :
                <MenuItem>loading...</MenuItem>
            }
        </TextField >
    );

};

export default Modes;