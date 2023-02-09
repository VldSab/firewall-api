import { MenuItem, Box } from "@mui/material";
import { TextField, Select } from 'formik-mui';
import { useEffect, useState } from "react";
import { groups } from "../../data/GroupsData"
const Groups = (props) => {

    const [names, setNames] = useState([]);

    useEffect(() => {
        const loadData = () => {
            setNames(JSON.parse(groups()).data.map(it => it.name))
        }
        loadData();
    }, []);

    return (
        <TextField 
            select
            {...props}
        >
            
            {names.length ?
                names.map((mode) => (
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

export default Groups;