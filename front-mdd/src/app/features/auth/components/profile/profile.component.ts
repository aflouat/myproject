import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from '../../../../shared/services/session.service';
import { TopicService } from '../../../topic/services/topic.service';
import { Topic } from '../../../topic/interfaces/topic.interface';
import { RegisterRequest } from '../../interfaces/register-request.interface';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss'],
    standalone: false
})
export class ProfileComponent implements OnInit {
  public profileForm: FormGroup;

  topics: Topic[] = []; // Liste des topics
  errorMessage: string = '';
  public onError = false;


  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private router: Router,
    private topicService:TopicService,
    private authService:AuthService
  ) {

    this.profileForm = this.fb.group({
      username: [ ''],
      email: [''],
      password: [''],
    });
  }

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    console.log('sessionInformation:', this.sessionService);
    if (this.sessionService.sessionInformation) {
      this.profileForm.patchValue({
        username: this.sessionService.sessionInformation.username,
        email: this.sessionService.sessionInformation.email

      });
    }
  }

  onSubmit(): void {
    console.log('Profil mis à jour :', this.profileForm.value);
    // Logique pour enregistrer les modifications
    if (this.profileForm.valid) {
       const registerRequest = this.profileForm.value as RegisterRequest;
     this.authService.update(registerRequest).subscribe({
       error: () => this.onError = true,
      });
    }
  }

  logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }

  unsubscribe(subscriptionId: number): void {
    console.log('Se désabonner de l’abonnement :', subscriptionId);
    // Logique pour se désabonner
        if (this.profileForm.valid || true) {
          console.log('Form Submitted', this.profileForm.value);
          const registerRequest = this.profileForm.value as RegisterRequest;
         this.authService.register(registerRequest).subscribe({
          next: () => this.router.navigate(['/auth/login']),
          error: () => this.onError = true,
          });
        }
  }

    // Récupérer les topics
    getUbscribedTopics(): void {
      console.log('list topics call.....')
      this.topicService.getSubscribedTopics().subscribe({
        next: (topics) => {
          this.topics = topics;
          console.log('topics:',JSON.stringify(topics, null, 2));
        },
        error: (err) => (this.errorMessage = 'Erreur lors du chargement des topics.'),
      });
    }
}
