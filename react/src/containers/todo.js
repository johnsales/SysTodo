import React from 'react'
import TodoPage from '../pages/todo'
import {create, read, update, remove} from '../services/api'

class Todo extends React.Component{
    
    constructor(props){
        super(props);

        this.state = {
            error: '',
            items : [],
            newItemText: '',
        };

        this.onNewItem = this.onNewItem.bind(this);
        this.onRemoveItem = this.onRemoveItem.bind(this);
        this.onChangeNewItemText = this.onChangeNewItemText.bind(this);
        this.onExitEditMode = this.onExitEditMode.bind(this);
        this.onEnterEditMode = this.onEnterEditMode.bind(this);
        this.onToogleItemComplete = this.onToogleItemComplete.bind(this);
    }

    async componentDidMount(){
        try {
            const items = await read();
            this.setState({items: JSON.parse(items)});

        } catch (error) {
            this.setState({error: error.message});
        }
    }

    onNewItem(){
        try {
          const newItem = [];//create({description: ''});
  
          this.setState({
            items:[
                ...this.state.items,
                {
                    ...newItem,
                    isEditting: true
                },
            ],
        })

        } catch (error) {
            
        }
    }

    async onRemoveItem(item){
        try {
            await remove(item.id);
            const { items } = this.state;
            const index = items.findIndex(n => n.id === item.id);

            if(index === -1){
                return;
            }

            const newItems = items.slice();
            newItems.splice(index, 1);
            this.setState({items : newItems});

        } catch (error) {
            window.location.reload();//TODO
            //this.setState({error: error.message});
        }
    }

    onChangeNewItemText(event){
        this.setState({ newItemText: event.target.value});
    }

    async onExitEditMode(item){
       if(item.id){ 
        try {        
            const{
                items,
                newItemText,
            } = this.state;
    
            const updatedItem = await update(item.id, newItemText);
            
            this.setState({
                newItemText:'',
                items : items.map((next) =>{
                    if(next.id === item.id){
                        return {
                            ...updatedItem,
                            isEditting : false,
                        };
                    }
    
                    return next;
                }),
            });
        } catch (error) {
          window.location.reload();//TODO
        }
       }else{
            const{
                items,
                newItemText,
            } = this.state;
            
          await create(newItemText);
          window.location.reload();//TODO
       }   
    }

    onEnterEditMode(item){
        const{
            items,
        } = this.state;

        this.setState({
            newItemText: item.description,
            items : items.map((next) =>{
                if(next.id === item.id){
                    return {
                        ...next,
                        isEditting : true
                    };
                }
                return next;
            }),
        });
    }

    async onToogleItemComplete(item){
        try {
           // console.log('item:' +!item.isChecked)
           console.log('check: '+ JSON.stringify(item.completedTask))
        const updatedItem = await update(item.id, "changeBoolean");   
        const{
            items,
        } = this.state;
/*
        this.setState({
            items : items.map((next) =>{
                if(next.id === item.id){
                    return updatedItem
                }
                return next;
            }),
        });*/
        } catch (error) {
            this.setState({error: error.message});
        }
    }
                            
    render(){
        const {
            items,
            newItemText,
            error,
        } = this.state;
        return (
            <TodoPage 
                items={items}
                newItemText={newItemText}
                onNewItem={this.onNewItem}
                onRemoveItem={this.onRemoveItem}
                onChangeNewItemText={this.onChangeNewItemText}
                onExitEditMode={this.onExitEditMode}
                onEnterEditMode={this.onEnterEditMode}
                onToogleItemComplete={this.onToogleItemComplete}
                error={error}
            />
        );
    }
}

export default Todo;