import React, { useState } from 'react';
import { createPost, updatePost } from '../services/postService';

interface PostFormProps {
  post?: any;
  onSuccess: () => void;
}

const PostForm: React.FC<PostFormProps> = ({ post, onSuccess }) => {
  const [title, setTitle] = useState(post?.title || '');
  const [content, setContent] = useState(post?.content || '');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (post) {
      await updatePost(post.id, { title, content });
    } else {
      await createPost({ title, content });
    }
    onSuccess();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={title}
        onChange={e => setTitle(e.target.value)}
        placeholder="Title"
      />
      <textarea
        value={content}
        onChange={e => setContent(e.target.value)}
        placeholder="Content"
      />
      <button type="submit">{post ? 'Update' : 'Create'}</button>
    </form>
  );
};

export default PostForm;