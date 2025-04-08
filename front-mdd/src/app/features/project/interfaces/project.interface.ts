export interface Project {
  id: number; // Unique identifier for the project
  name: string; // Name of the project
  description: string; // Description of the project
  startDate?: Date; // Optional start date of the project
  endDate?: Date; // Optional end date of the project
  kpis?: { [key: string]: number }; // Optional key performance indicators (KPI) for the project
}
