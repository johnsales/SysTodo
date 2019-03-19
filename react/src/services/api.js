const baseUrl = 'http://localhost:8080';

async function request(url, method, data){
   try {
    const response = await fetch(`${baseUrl}${url}`,{
        method,
        body: data ? JSON.stringify(data) : undefined,
        
    });

    const jsonResponse = await response.json();
    
    if(response.status !== 200){
        let error;
        if(jsonResponse && jsonResponse.errors){
            error = jsonResponse.errors[0].message;
        }        
        throw Error(error || 'There was an error');
    }

    return JSON.stringify(jsonResponse);

    } catch (error) {
        window.location.reload();//TOFIX
   }
}

export function create(data) {
    return request('/SysTodo/rest/taskWS', 'POST', data);
}

export function read() {
    return request('/SysTodo/rest/taskWS', 'GET');
}

export function update(id, data) {
    return request(`/SysTodo/rest/taskWS/update/${id}`, 'POST', data);
}

export function remove(id) {
    return request(`/SysTodo/rest/taskWS/${id}`, 'POST');
}