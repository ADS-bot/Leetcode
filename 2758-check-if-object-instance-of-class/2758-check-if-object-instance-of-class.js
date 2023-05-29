var checkIfInstanceOf = function(obj, classFunction) {
    if (obj == null) return false;
    if(typeof classFunction !== 'function') return false;
    return Object(obj) instanceof classFunction;
};var checkIfInstanceOf = function(obj, classFunction) {
    if (obj == null) return false;
    if(typeof classFunction !== 'function') return false;
    return Object(obj) instanceof classFunction;
};