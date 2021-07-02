export class Produto {
    id: number;
    name: string;
    description: string;
    unit: string;

    constructor(id: number, name: string, description: string, unit: string) {
        this.id = id,
        this.name = name;
        this.description = description;
        this.unit = unit;
    }
}
