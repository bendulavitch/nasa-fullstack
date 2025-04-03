<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors" class="error-message">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
#register {
  background-color: #1e1e2f;
  color: #d1d1d1;
  padding: 3rem;
  border-radius: 10px;
  max-width: 400px;
  margin: 0 auto;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
  text-align: center;
}

h1 {
  font-family: 'Orbitron', sans-serif;
  font-size: 2rem;
  color: #f0f0f0;
  margin-bottom: 1.5rem;
}

.form-input-group {
  margin-bottom: 1.5rem;
}

label {
  font-size: 1rem;
  color: #ccc;
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.8rem;
  font-size: 1rem;
  border: 1px solid #444;
  border-radius: 5px;
  background-color: #2b2b42;
  color: #d1d1d1;
  margin-top: 0.3rem;
}

input:focus {
  outline: none;
  border: 1px solid #4b86f7;
  background-color: #3a3a56;
}

button {
  background-color: #4b86f7;
  color: #fff;
  padding: 0.8rem 2rem;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

button:hover {
  background-color: #365cbf;
}

p {
  margin-top: 1.5rem;
  color: #ccc;
}

a {
  color: #4b86f7;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

.error-message {
  background-color: #ff5555;
  color: #fff;
  padding: 0.8rem;
  margin-bottom: 1rem;
  border-radius: 5px;
  font-size: 1rem;
}
</style>
