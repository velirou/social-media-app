import api from './api';

export const getPosts = () => api.get('/posts');
export const getPost = (id: number) => api.get(`/posts/${id}`);
export const createPost = (post: any) => api.post('/posts', post);
export const updatePost = (id: number, post: any) => api.put(`/posts/${id}`, post);
export const deletePost = (id: number) => api.delete(`/posts/${id}`);